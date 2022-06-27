package ClinicManagement.service;


import ClinicManagement.entity.MedicineTime;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.MedicineTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

@Service
public class MedicineTimeService {

    @Autowired
    MedicineTimeRepository repository;

    public MedicineTime addMedTime(MedicineTime medicineTime)
    {
        try {
            return repository.save(medicineTime);
        }
        catch (Exception ex)
        {
            throw new ReplicatedException();
        }
    }

    public MedicineTime updateMedTime(MedicineTime medicineTime)
    {
        try {
            return repository.save(medicineTime);
        }
        catch (Exception ex)
        {
            throw new ReplicatedException();
        }
    }


    public List<MedicineTime> getMedTimes(boolean isActive)
    {
        List<MedicineTime> medTimes = repository.findByIsActive(isActive);
        if (medTimes.isEmpty()) throw new NotFoundException();
        return medTimes;
    }
    public List<MedicineTime> getMedTimesForPatient(int patientId,boolean isActive)
    {
        List<MedicineTime> medTimes = repository.findByPatientAndIsActive(patientId,isActive);
        if(medTimes.isEmpty()) throw  new NotFoundException();
        return medTimes;
    }

    public MedicineTime getMedTimeById(int id,boolean isActive)
    {
        try {
            return repository.findByMedicineTimeIdAndIsActive(id,isActive).get();
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException();
        }
    }

    public void activeMedTime(int id,boolean isActive)
    {
        try {
            MedicineTime medicineTime = repository.findById(id).get();
            medicineTime.setActive(isActive);
            repository.save(medicineTime);
        }
        catch (NoSuchElementException ex)
        {
            throw new NotFoundException("Can't active or non-active for not found object");
        }
    }

    public List<MedicineTime> getMedTimeByTime(Time time , boolean isActive)
    {
        List<MedicineTime> medT = repository.findByMedTimeAndIsActive(time,isActive);
        if(medT.isEmpty()) throw new NotFoundException();
        return medT;
    }


}
