package com.fpmislata.daw1.projectedaw1.data;

import com.fpmislata.daw1.projectedaw1.domain.entity.Usuari;

import java.time.LocalDate;
import java.util.List;

public class UsuariData {
    public static final List<Usuari> USUARI_LIST = List.of(
            new Usuari("user1", "user1@localhost", LocalDate.parse("2021-01-01"), "$2a$10$7P2f2u72PfbOGJtL7CQTruW5WZ0.cgT9jUbnbfo.2wvE.gaaYVvn2"), /* password: user1 */
            new Usuari("user2", "user2@localhost", LocalDate.parse("2021-01-01"), "$2a$10$6wkDziA7C5460lfnzdXWbuLdSHUz.m8McydWD3ToZfBgOgE7nPQE."), /* password: user2 */
            new Usuari("user3", "user3@localhost", LocalDate.parse("2021-01-01"), "$2a$10$il0FptkiuLRtF7swrx7KoudhNtw0VIQHjWtX4.RNFzFA7l5TFIs.i"), /* password: user3 */
            new Usuari("user4", "user4@localhost", LocalDate.parse("2021-01-01"), "$2a$10$6vcrnNp68l5RnW6Mh56CV.KBo8oM1xgY/Asb0vIYV/.3TwMVbhX5G") /* password: user4 */
    );
}
