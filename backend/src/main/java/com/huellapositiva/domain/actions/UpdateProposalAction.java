package com.huellapositiva.domain.actions;

import com.huellapositiva.application.dto.UpdateProposalRequestDto;
import com.huellapositiva.domain.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class UpdateProposalAction {

    public final ProposalService proposalService;

    public void execute(UpdateProposalRequestDto updateProposalRequestDto, String accountId) throws ParseException {
        proposalService.updateProposal(updateProposalRequestDto, accountId);
        //UpdateProfileResult result = profileService.updateProfile(updateProfileRequestDto, accountId);
        /*if (result.isNewEmail()) {
            EmailConfirmation emailConfirmation = EmailConfirmation.from(updateProfileRequestDto.getEmail(), emailConfirmationBaseUrl);
            emailCommunicationService.sendMessageEmailChanged(emailConfirmation);
        }*/
    }

}