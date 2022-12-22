package app.center.service;

import app.center.model.BloodBag;
import app.center.model.Center;
import app.center.repository.IBloodBagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodBagService implements IBloodBagService {

    @Autowired
    private IBloodBagRepository bloodBagRepository;

    public List<BloodBag> getAll() {
        return bloodBagRepository.findAll();
    }
    public BloodBag save(BloodBag bloodBag)
    {
        return bloodBagRepository.save(bloodBag);
    }
}
