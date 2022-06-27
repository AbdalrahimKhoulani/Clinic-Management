package ClinicManagement.notification;

import ClinicManagement.entity.*;
import ClinicManagement.service.AppointmentService;
import ClinicManagement.service.MedicineTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Date;
import java.sql.Time;
import java.time.*;
import java.util.*;


public class Notify {

   @Autowired
   private AppointmentService appointmentService;

   @Autowired
   private MedicineTimeService medicineTimeService;


    private final Logger logger = LoggerFactory.getLogger(Notify.class);
    private  PushNotificationService pushNotificationService;
    public Notify(){

        pushNotificationService = new PushNotificationService(new FCMService());

    }


    private Date getNextDay()
    {
        return Date.valueOf(LocalDate.now().plusDays(1));
    }
    private Time getNextTime() {
        return  Time.valueOf(LocalTime.now().plusHours(1));
    }


    public void startAppointmentNotification() {

        List<Appointment> appointmentToday = appointmentService.getAppointmentByDateAndStatus(getNextDay(),"ACCEPT",true);

        List<Token> tokenOfDoctors = new ArrayList<>();
        List<Token> tokenOfPatients = new ArrayList<>();

        for (Appointment app : appointmentToday)
        {
            Doctor doctor = app.getDoctor();
            Patient patient = app.getPatient();

            tokenOfDoctors.add(doctor.getToken());
            tokenOfPatients.add(patient.getToken());

        }
       sendNotification(tokenOfDoctors,1);
       sendNotification(tokenOfPatients,1);
    }
    public void startMedicineTimeNotification() {
        List<MedicineTime> medicineTimes = medicineTimeService.getMedTimeByTime(getNextTime(),true);

        List<Token> tokenOfPatients = new ArrayList<>();


        for (MedicineTime medT : medicineTimes)
        {
            PatientMedicine patientMedicine = medT.getPatientMedicine();
            Patient patient = patientMedicine.getPatient();
            tokenOfPatients.add(patient.getToken());
        }

        sendNotification(tokenOfPatients,2);

    }
    private void sendNotification(List<Token> tokens,int type) {
        int i = 0;
        for (Token token:tokens)
        {
            switch (type)
            {
                case 1:{
                    /**Create PushNotificationRequest to send Notification **/
            /*PushNotificationRequest request = new PushNotificationRequest("greeting message","I am Eyad SA","no topic",token);
           // request.setToken("e7n_8tRgRMU:APA91bHMXC5vSMtts7nfi4S5-I6pOnYZGY6Ybyk41GyG27lcPXyM1PeBFpeNrAjR_I6PqRLjS3k06c4-RrVxNLo0Us25QORUDJxr0lsrOitHItwMfRsCHNrqhSyQYES06D_HDUrIKyJ7");
            pushNotificationService.sendPushNotificationToToken(request);
            logger.info("The notification has been sent ");*/
                    break;
                }
                case 2:{
                    /**Create PushNotificationRequest to send Notification with different message body **/
            /*PushNotificationRequest request = new PushNotificationRequest("greeting message","I am Eyad SA","no topic",token);
           // request.setToken("e7n_8tRgRMU:APA91bHMXC5vSMtts7nfi4S5-I6pOnYZGY6Ybyk41GyG27lcPXyM1PeBFpeNrAjR_I6PqRLjS3k06c4-RrVxNLo0Us25QORUDJxr0lsrOitHItwMfRsCHNrqhSyQYES06D_HDUrIKyJ7");
            pushNotificationService.sendPushNotificationToToken(request);
            logger.info("The notification has been sent ");*/
                    break;
                }
                default:break;
            }
            /**Create PushNotificationRequest to send Notification **/
            /*PushNotificationRequest request = new PushNotificationRequest("greeting message","I am Eyad SA","no topic",token);
           // request.setToken("e7n_8tRgRMU:APA91bHMXC5vSMtts7nfi4S5-I6pOnYZGY6Ybyk41GyG27lcPXyM1PeBFpeNrAjR_I6PqRLjS3k06c4-RrVxNLo0Us25QORUDJxr0lsrOitHItwMfRsCHNrqhSyQYES06D_HDUrIKyJ7");
            pushNotificationService.sendPushNotificationToToken(request);
            logger.info("The notification has been sent ");*/
            System.out.println(i++);
        }
    }

}

