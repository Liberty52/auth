package com.liberty52.auth.global.event.eventhandler;

import com.liberty52.auth.global.adapter.MailSender;
import com.liberty52.auth.global.adapter.MailSender.Mail;
import com.liberty52.auth.global.event.events.MemberDeletedEvent;
import com.liberty52.auth.global.event.events.SendMailEvent;
import com.liberty52.auth.global.utils.EmailPageFormatter;
import com.liberty52.auth.service.applicationservice.QuestionDeleteService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
@Transactional
public class MemberDeleteEventHandler {

    private final MailSender mailSender;
    private final QuestionDeleteService questionDeleteService;
    @Async
    @EventListener(MemberDeletedEvent.class)
    public void handlingSendMailEvent(MemberDeletedEvent memberDeletedEvent) throws MessagingException {
        Mail mail = MailSender.buildMail(memberDeletedEvent.getEmail(), memberDeletedEvent.getTitle(),
                EmailPageFormatter.formatSignUpOrDeletePage(memberDeletedEvent.getMainText(), memberDeletedEvent.getSubText(),
                        memberDeletedEvent.getName(), memberDeletedEvent.getEmail(), memberDeletedEvent.getDateText(), memberDeletedEvent.getDate()), true);
        mailSender.prepare(mail);
        mailSender.send();
        questionDeleteService.deleteAllQuestion(memberDeletedEvent.getAuthId());

        // Kafka Event Publish
    }

}
