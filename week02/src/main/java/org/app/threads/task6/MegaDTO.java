package org.app.threads.task6;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MegaDTO {
    private IPAddressDTO ipAddressDTO;
    private DigitalOceanStatusDTO digitalOceanStatusDTO;
    private YesOrNoDTO yesOrNoDTO;
    private ChuckNorrisDTO chuckNorrisDTO;
    private KanyeWestDTO kanyeWestDTO;
    private DonaldTrumpDTO donaldTrumpDTO;

    public MegaDTO(IPAddressDTO ipAddressDTO, DigitalOceanStatusDTO digitalOceanStatusDTO, YesOrNoDTO yesOrNoDTO, ChuckNorrisDTO chuckNorrisDTO, KanyeWestDTO kanyeWestDTO, DonaldTrumpDTO donaldTrumpDTO) {
        this.ipAddressDTO = ipAddressDTO;
        this.digitalOceanStatusDTO = digitalOceanStatusDTO;
        this.yesOrNoDTO = yesOrNoDTO;
        this.chuckNorrisDTO = chuckNorrisDTO;
        this.kanyeWestDTO = kanyeWestDTO;
        this.donaldTrumpDTO = donaldTrumpDTO;
    }
}
