package com.example.service.impl;

import com.example.controller.FlatController;
import com.example.exception.ResourceNotFoundException;
import com.example.service.EmailSenderService;
import com.example.service.EmailSenderServiceHTML;
import com.example.service.FlatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.repository.FlatRepository;
import com.example.model.Flat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FlatImpl implements FlatService {

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private EmailSenderServiceHTML senderServiceHTML;
    Logger logger = LoggerFactory.getLogger(FlatController.class);


    @Override
    public List<Flat> getAllFlats() {
        logger.info("/flats adresine get request gönderilmiştir.");
        return flatRepository.findAll();
    }

    @Override
    public ResponseEntity<Flat> getFlatById(Long id) {
        try {
            Flat flat = flatRepository.findById(id)
                    .orElse(null);
            if (flat != null) {
                logger.info("Daire bulundu ID: " + id);
                return ResponseEntity.ok(flat);
            } else {
                logger.warn("Daire bulunamadi ID: " + id);
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            logger.error("An unexpected error occurred", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public Flat createFlat(Flat flat) {
        //if (savedFlat != null)
        //savedFlat asla null dönmeyeceğinden yukarıdaki ifade always true oluyor başka bir kontrol yöntemi lazım
        //dikkat aşağıda yalnızca sql save işleminin başarıyla tamamlandığı kontrolü var
        try {
            Flat savedFlat = flatRepository.save(flat);
            logger.info("Flat ID: " + savedFlat.getId() + "olan daire başarıyla veritabanına kaydedilmiştir.");

            // Başlık ve gövdeyi oluştur
            String subject = savedFlat.getId() + " numaralı ilan oluşturulmuştur";
            String body = savedFlat.getId() + " numaralı ilan oluşturulmuştur";

            // E-posta gönderme işlemi
            //senderService.sendSimpleEmail("olmez.ee@gmail.com", subject, body);

            senderServiceHTML.sendEmailWithTemplate("olmez.ee@gmail.com",subject,body);


            return savedFlat;
        } catch (Exception ex) {
            logger.error("Daire kaydedilemedi", ex);
            // Burada isteğe bağlı olarak hata mesajını ve durum kodunu döndürebilirsiniz.
            throw new RuntimeException("Daire kaydedilemedi", ex);
        }
        //return flatRepository.save(flat);
    }

    @Override
    public ResponseEntity<List<Flat>> getFlatsByPage(int page) {
        try {
            // Sayfalama için Pageable oluştur
            Pageable pageable = PageRequest.of(page - 1, 5); // Her sayfa 5 satır alacak şekilde
            Page<Flat> flatPage = flatRepository.findAll(pageable);

            if (!flatPage.isEmpty()) {
                logger.info("Sayfa " + page + " için daireler bulundu.");
                // Toplam sayfa sayısını bul
                int totalPages = flatPage.getTotalPages();
                // Toplam satır sayısını bul
                long totalElements = flatPage.getTotalElements();
                // Sayfa başına gelen daireleri al
                List<Flat> flats = flatPage.getContent();
                // Yanıtı oluştur
                return ResponseEntity.ok()
                        .header("X-Total-Pages", String.valueOf(totalPages))
                        .header("X-Total-Elements", String.valueOf(totalElements))
                        .body(flats);
            } else {
                logger.warn("Sayfa " + page + " için daire bulunamadı.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            logger.error("Beklenmeyen bir hata oluştu", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @Override
    public ResponseEntity<Flat> updateFlat(Long id, Flat flatDetails) {
        try {
            Flat flat = flatRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Bu id'ye sahip bir daire bulunamadi. ID: " + id));

            flat.setTitle(flatDetails.getTitle());
            flat.setRoom(flatDetails.getRoom());
            flat.setFloor(flatDetails.getFloor());
            flat.setAge(flatDetails.getAge());
            flat.setBalcony(flatDetails.getBalcony());
            flat.setHeating(flatDetails.getHeating());
            flat.setOtopark(flatDetails.getOtopark());
            flat.setM2(flatDetails.getM2());
            flat.setPrice(flatDetails.getPrice());
            flat.setTapu(flatDetails.getTapu());

            Flat updatedFlat = flatRepository.save(flat);
            logger.info("Flat with ID " + id + " updated successfully");
            String subject = updatedFlat.getId() + " numaralı daire güncellenmiştir.";
            String body = updatedFlat.getId() + " numaralı daire güncellenmiştir.";

            // E-posta gönderme işlemi
            senderServiceHTML.sendEmailWithTemplate("olmez.ee@gmail.com",subject,body);
            return ResponseEntity.ok(updatedFlat);
        } catch (ResourceNotFoundException ex) {
            logger.warn(ex.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            logger.error("An unexpected error occurred", ex);
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/flats/{id}")
    public ResponseEntity<Flat> deleteFlat(Long id) {
        try {
            Flat flat = flatRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Bu id'ye sahip daire bulunamadi " + id));

            flatRepository.delete(flat);

            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);

            logger.info("Daire ID: " + id + " başarıyla silindi.");
            String subject = id + " numaralı ilan silinmiştir.";
            String body = id + " numaralı ilan silinmiştir.";

            // E-posta gönderme işlemi
            senderServiceHTML.sendEmailWithTemplate("olmez.ee@gmail.com",subject,body);

            return ResponseEntity.ok().body(flat);
        } catch (ResourceNotFoundException ex) {
            logger.warn("Daire bulunamadi ID: " + id);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            logger.error("Daire silinirken beklenmeyen bir hata oluştu ID: " + id, ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
