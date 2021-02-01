package com.huellapositiva.domain.actions;

import com.huellapositiva.application.dto.ProposalRevisionDto;
import com.huellapositiva.domain.model.entities.ContactPerson;
import com.huellapositiva.domain.model.entities.ESAL;
import com.huellapositiva.domain.model.entities.Reviser;
import com.huellapositiva.domain.model.valueobjects.Id;
import com.huellapositiva.domain.model.valueobjects.ProposalRevisionEmail;
import com.huellapositiva.domain.model.valueobjects.ProposalRevisionEmail.ProposalRevisionEmailBuilder;
import com.huellapositiva.domain.model.valueobjects.Token;
import com.huellapositiva.domain.repository.ContactPersonRepository;
import com.huellapositiva.domain.repository.CredentialsRepository;
import com.huellapositiva.domain.repository.ProposalRepository;
import com.huellapositiva.domain.service.EmailCommunicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class SubmitProposalRevisionAction {

    private final EmailCommunicationService communicationService;

    private final ProposalRepository proposalRepository;

    private final ContactPersonRepository contactPersonRepository;

    private final CredentialsRepository credentialsRepository;

    /**
     * Fetch the ESAL of the database for get the ContactPerson and send an email with the revision of the reviser.
     *
     * @param proposalId : The id of the proposal to be revised.
     * @param revisionDto : Contains the email reviser and the feedback if has it.
     * @param proposalURI : URI the proposal to revise.
     * @param accountId Account ID of logged user
     */

    public void execute(String proposalId, ProposalRevisionDto revisionDto, URI proposalURI, String accountId) {
        ESAL esal = proposalRepository.fetch(proposalId).getEsal();
        ContactPerson contactPerson = contactPersonRepository.findByJoinedEsalId(esal.getId().toString());
        Reviser reviser = Reviser.from(credentialsRepository.findReviserByAccountId(accountId));

        ProposalRevisionEmailBuilder revisionBuilder = ProposalRevisionEmail.builder()
                .proposalId(new Id(proposalId))
                .proposalURI(proposalURI)
                .feedback(revisionDto.getFeedback())
                .esalContactPerson(contactPerson)
                .reviser(reviser)
                .token(Token.createToken());

        Boolean hasFeedback =  revisionDto.getHasFeedback();
        if (hasFeedback != null && hasFeedback && revisionDto.getFeedback() == null) {
            revisionBuilder.hasFeedback(false);
        } else {
            revisionBuilder.hasFeedback(hasFeedback);
        }

        communicationService.sendSubmittedProposalRevision(revisionBuilder.build());
    }
}
