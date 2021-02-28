package com.huellapositiva.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class UpdateProposalRequestDto {

    @Schema(
            description = "Id of the proposal",
            example = "abcd1234-abcd-1234-abcd-abcdef1234561"
    )
    @NotBlank
    private final String id;

    @Schema(
            description = "Title of the proposal",
            example = "Recogida de ropa"
    )
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$")
    private final String title;

    @Schema(
            description = "Where the user lives",
            example = "Las Palmas"
    )
    @Pattern(regexp = "^(Las Palmas|Santa Cruz de Tenerife)$")
    private final String province;

    @Schema(
            description = "Where the user lives. TIP: Can be +35xxx or 38xxx",
            example = "35241"
    )
    @NotBlank
    @Pattern(regexp = "^\\d{5}$")
    private final String zipCode;

    @Schema(
            description = "Where the user lives",
            example = "Agaete"
    )
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private final String town;

    @Schema(
            description = "Where the user lives",
            example = "Calle Guacimeta N2"
    )
    private final String address;

    @Schema(
            description = "Where the user lives. TIP: Only can be the eight islands of Canary Islands",
            example = "Gran Canaria"
    )
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z ]*$")
    private final String island;

    @Schema(
            description = "The period of days to volunteer",
            example = "Weekend"
    )
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$")
    private final String requiredDays;

    @Schema(
            description = "The minimum age to participate in volunteering",
            example = "20"
    )
    @NotNull
    private final int minimumAge;

    @Schema(
            description = "The maximum age to participate in volunteering",
            example = "50"
    )
    @NotNull
    private final int maximumAge;

    @Schema(
            description = "Starting the enrollment of volunteers for the proposal, " +
                    "is before than closeProposalDate and startingVolunteeringDate",
            example = "1990-06-27"
    )
    private final LocalDate startingProposalDate;

    @Schema(
            description = "Close the enrollment of volunteers for the proposal, " +
                    "is before than startingVolunteeringDate",
            example = "1990-06-27"
    )
    @NotNull
    private final LocalDate closingProposalDate;

    @Schema(
            description = "Date the volunteering starts",
            example = "1990-06-27"
    )
    @NotNull
    private final LocalDate startingVolunteeringDate;

    @Schema(
            description = "The description of the proposal",
            example = "Una propuesta de voluntariado para recoger ropa en centros comerciales"
    )
    @NotBlank
    private final String description;

    @Schema(
            description = "The duration of days of a volunteer proposal",
            example = "20"
    )
    @NotBlank
    @Pattern(regexp = "^[0-9]*$")
    private final String durationInDays;

    @Schema(
            description = "The way to make the volunteer proposal: on site, remote or mixed",
            example = "ON_SITE"
    )
    @NotBlank
    private final String category;

    @Schema(
            description = "The necessary skills to carry out specific volunteer actions",
            example = "{{'Fuerte'}, {'Se van a mover cargas bastante pesadas'}}"
    )
    private final String[][] skills;

    @Schema(
            description = "The necessary skills to carry out specific volunteer actions",
            example = "{'Es necesario disponer del DNI'}"
    )
    private final String[] requirements;

    @Schema(
            description = "Extra information for the volunteers",
            example = "Cuidado hay desprendimientos en la zona"
    )
    private final String extraInfo;

    @Schema(
            description = "Instructions for volunteering",
            example = "Debes de aparcar el coche en el aparcamiento del lugar, mostrar tu DNI y aparcar tranquilamente"
    )
    private final String instructions;
}
