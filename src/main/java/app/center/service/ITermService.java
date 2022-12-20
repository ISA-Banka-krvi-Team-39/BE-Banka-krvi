package app.center.service;

import app.center.model.Term;

import java.util.List;

public interface ITermService {
    public Term create(Term term);
    public Term findOne(Integer id);
    public List<Term> getAll();

}
