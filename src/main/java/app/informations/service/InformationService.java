package app.informations.service;

import app.center.model.Center;
import app.center.repository.ICenterRepository;
import app.informations.model.Informations;
import app.informations.repository.IInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationService implements IInformationService{
    @Autowired
    private IInformationRepository informationRepository;
    @Override
    public Informations create(Informations info) { return informationRepository.save(info);}
}
