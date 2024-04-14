package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.AutorRecord;

import java.time.LocalDate;
import java.util.List;

public class AutorTableMemory {
    private final List<AutorRecord> autorRecordList = List.of(
            new AutorRecord(
                    0,
                    "Irene Solà",
                    "Irene Solà (Malla, 1990) és escriptora i traductora. Ha publicat les novel·les Canto jo i la muntanya balla (2019) i Míriam (2021), a més de diversos llibres de poesia i contes. La seva obra ha estat traduïda a més de vint idiomes i ha rebut diversos premis, com el Documenta, el Llibres Anagrama de Novel·la i el Premi de la Crítica de narrativa catalana. Ha traduït al català obres de Virginia Woolf, Ali Smith, W. G. Sebald, Joan Didion i Anne Carson, entre d'altres. És doctora en Humanitats per la Universitat Pompeu Fabra i ha viscut a Londres, París, Berlín i Brussel·les. Actualment resideix a Barcelona.",
                    LocalDate.parse("1990-08-17"),
                    "irene_sola.jpg"
            ),
            new AutorRecord(
                    1,
                    "Sara Mesa",
                    "Sara Mesa (Madrid, 1976) és escriptora i traductora. Ha publicat les novel·les Un incendi arran de terra (2008), El trencanous (2011), Cicatriz (2015) i Mala letra (2016), a més de diversos llibres de relats i poesia. La seva obra ha estat traduïda a més de vint idiomes i ha rebut diversos premis, com el Ojo Crítico de Narrativa, el Premio Málaga de Ensayo i el Premio Nacional de Narrativa. Ha traduït al castellà obres de Virginia Woolf, Ali Smith, W. G. Sebald, Joan Didion i Anne Carson, entre d'altres. És llicenciada en Filologia Hispànica per la Universitat Complutense de Madrid i ha viscut a Londres, París, Berlín i Brussel·les. Actualment resideix a Barcelona.",
                    LocalDate.parse("1976-06-30"),
                    "sara_mesa.jpg"
            ),
            new AutorRecord(
                    2,
                    "Issac Asimov",
                    "Isaac Asimov (Petróvichi, 1920 - Nova York, 1992) va néixer a Rússia i va emigrar als Estats Units amb la seva família quan era un nen. Va estudiar a la Universitat de Columbia i es va doctorar en química. Va ser professor de bioquímica a la Universitat de Boston i va publicar més de cinc-cents llibres, entre els quals destaquen les seves obres de divulgació científica i les seves novel·les de ciència-ficció. Va ser un dels autors més prolífics de la història de la literatura i va rebre nombrosos premis, com el Hugo, el Nebula i el Bram Stoker. La seva obra més coneguda és la sèrie de la Fundació, que va ser escollida com la millor sèrie de ciència-ficció de tots els temps per la World Science Fiction Society.",
                    LocalDate.parse("1920-01-02"),
                    "isaac_asimov.webp"
            )
    );

    public List<AutorRecord> get(){
        return autorRecordList;
    }
}
