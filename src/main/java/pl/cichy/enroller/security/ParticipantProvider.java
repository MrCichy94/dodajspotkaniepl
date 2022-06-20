package pl.cichy.enroller.security;

import pl.cichy.enroller.model.Participant;
import pl.cichy.enroller.persistence.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ParticipantProvider implements UserDetailsService {
    @Autowired
    private ParticipantService participantService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Participant participant = participantService.findByLogin(username);
        if (participant == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(participant.getLogin(), participant.getPassword(), Collections.emptyList());
    }
}
