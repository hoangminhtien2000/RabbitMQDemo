package com.example.rabbitmqtutorial.consumer;

import com.example.rabbitmqtutorial.constants.BillConstants;
import com.example.rabbitmqtutorial.domain.dto.BillDTO;
import com.example.rabbitmqtutorial.domain.dto.MessageDTO;
import com.example.rabbitmqtutorial.domain.entity.Bill;
import com.example.rabbitmqtutorial.repository.BillRepo;
import com.example.rabbitmqtutorial.util.Converter;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitMQConsumer {

    private final BillRepo billRepo;

    public RabbitMQConsumer(BillRepo billRepo) {
        this.billRepo = billRepo;
    }

    @RabbitListener(queues = "${rabbitmq.queue.listen}")
    public void onMessage(MessageDTO messageDTO) {
        System.out.println("Receiver message from RabbitMQListen: " + messageDTO);
        Bill bill = Converter.toObject(messageDTO.getData(), Bill.class);
        if (bill.getStatus().equals(BillConstants.Status.CREATING)) {
            bill.setStatus(BillConstants.Status.PENDING);
        }
        billRepo.save(bill);
    }

    @RabbitListener(queues = "${rabbitmq.queue.rollback}")
    public void rollbackMessage(MessageDTO messageDTO) {
        System.out.println("Receiver message rollback from RabbitMQListen: " + messageDTO);
        Bill bill = Converter.toObject(messageDTO.getData(), Bill.class);
        billRepo.delete(bill);
    }

    @Transactional
    @RabbitListener(queues = "${rabbitmq.queue.listen2}")
    public void onMessageListen2(MessageDTO messageDTO) {
        System.out.println("Receiver message from RabbitMQListen2: " + messageDTO);
        BillDTO bill = Converter.toObject(messageDTO.getData(), BillDTO.class);
        Optional<Bill> response = billRepo.findById(bill.getId());
        if (response.isPresent()) {
            response.get().setShipperId(bill.getShipperId());
            if (messageDTO.getReason().equals(BillConstants.Status.SHIPPING)) {
                response.get().setStatus(BillConstants.Status.SHIPPING);
            }
            if (messageDTO.getReason().equals(BillConstants.Status.SUCCESS)) {
                response.get().setStatus(BillConstants.Status.SUCCESS);
            }
        //Không cần lưu vì Hibernate đã tự động cập nhật đối tượng dựa vào billRepo.findById(bill.getId())
        }else System.out.printf("Đơn hàng có 'id': %d không tồn tại\n", bill.getId());

    }

}
