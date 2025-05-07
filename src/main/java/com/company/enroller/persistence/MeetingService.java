package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;

	public MeetingService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public Collection<Participant> getParticipants(long id) {
		Meeting newMeeting = findById(id);
		Collection<Participant> participants = newMeeting.getParticipants();
		return participants;
	}

	public Participant getParticipant(long id, Participant participant) {
		Collection<Participant> participants =  getParticipants( id);
		Participant foundParticipant = participants.iterator().next();
		return foundParticipant;
	}

	public Meeting add(Meeting meeting, Participant participant) {
//		Meeting meeting = findById(id);
		Transaction transaction = connector.getSession().beginTransaction();
		meeting.addParticipant(participant);
		connector.getSession().save(meeting);
		transaction.commit();
		return meeting;
	}

	public Meeting findById(long id) {
		return connector.getSession().get(Meeting.class, id);
	}

	public Meeting add(Meeting meeting) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(meeting);
		transaction.commit();
		return meeting;
	}


	public void update(Meeting meeting) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().merge(meeting);
		transaction.commit();
	}

	public void delete(Meeting meeting) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().delete(meeting);
		transaction.commit();
	}

	public Meeting getMeeting(long id) {
		return connector.getSession().get(Meeting.class, id);
	}




}
