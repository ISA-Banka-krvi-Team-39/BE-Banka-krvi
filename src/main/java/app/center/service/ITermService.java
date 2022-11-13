package app.center.service;

import app.center.model.Term;

public interface ITermService {
    public Term create(Term term);
    public Term findOne(Integer id);
}
