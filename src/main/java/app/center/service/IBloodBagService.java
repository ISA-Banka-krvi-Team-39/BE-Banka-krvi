package app.center.service;

import app.center.model.BloodBag;

import java.util.List;

public interface IBloodBagService {

    List<BloodBag> getAll();

    BloodBag save(BloodBag bloodBag);
}
