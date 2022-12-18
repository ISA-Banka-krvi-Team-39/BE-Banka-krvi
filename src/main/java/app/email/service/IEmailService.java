package app.email.service;

import app.email.model.EmailDetails;

public interface IEmailService {
    void sendWelcomeMail(EmailDetails email);
}
