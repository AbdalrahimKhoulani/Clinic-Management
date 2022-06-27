package ClinicManagement;



import ClinicManagement.notification.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;


@Configuration
@SpringBootApplication
@EnableScheduling
public class Start {

    private final long appointmentTime = 12 * 60  * 60 * 1000; /**  12 hour in milliseconds   */
    private final long medicineTime = 60 * 60 * 1000; /**  1 hour in milliseconds   */
    private final Notify  notify = new Notify();

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Start.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "5050"));
        app.run(args);
    }

   /* @Scheduled(initialDelay = 1000L , fixedRate = 3000L)
    public void startAppointmentNotification(){
        notify.startAppointmentNotification();
    }
    @Scheduled(initialDelay = 1000L,fixedRate = 3000L)
    public void startMedicineTimeNotification(){
        notify.startMedicineTimeNotification();
    }*/



}

