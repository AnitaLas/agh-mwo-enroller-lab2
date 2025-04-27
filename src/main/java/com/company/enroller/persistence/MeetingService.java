package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {

	DatabaseConnector connector;
	Session meeting;

	public MeetingService() {
		meeting = DatabaseConnector.getInstance().getSession();
	}

	public Collection<Meeting> getAll() {
		String hql = "FROM Meeting";
		Query query = this.meeting.createQuery(hql);
		return query.list();
	}


	public Participant findById(long id) {
		return connector.getSession().get(Meeting.class, id);
	}

}
