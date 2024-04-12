package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data;

import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;

import java.time.LocalDate;
import java.util.List;

public class LlibreTableMemory {
    private final List<LlibreRecord> llibreRecordList = List.of(
            new LlibreRecord(
                    "9788433915689",
                    "Canto jo i la muntanya balla",
                    "Canto jo i la muntanya balla",
                    "Canto jo i la muntanya balla és una novel·la d'Irene Solà publicada el 2019, de les més venudes a la literatura catalana contemporània, que ha arribat també al mercat internacional i ha estat traduïda a una vintena d'idiomes. El 2021 va ser la novel·la més prestada a la xarxa de biblioteques de Barcelona.",
                    "Summary in english",
                    LocalDate.parse("2019-01-01"),
                    162,
                    "canto_jo.jpeg"
            ),
            new LlibreRecord(
                    "9788433999030",
                    "Un amor",
                    "Un amor",
                    "Nat, una joven e inexperta traductora, acaba de mudarse al pequeño núcleo rural de La Escapa. Su casero, que le regala un perro como gesto de bienvenida, no tardará en mostrar su verdadera cara y los conflictos en torno a la pobre casa alquilada se convertirán en una verdadera obsesión para ella. El resto de habitantes de la zona –la chica de la tienda, Píter el hippie, la vieja y demente Roberta, Andreas el alemán...– acogerán a Nat con aparente normalidad, mientras de fondo laten la incomprensión y la extrañeza mutuas. La Escapa terminará adquiriendo una personalidad propia, oprimente y confusa, que enfrentará a Nat no solo con sus vecinos, sino también consigo misma y sus propios fracasos.",
                    "Summary in english",
                    LocalDate.parse("2020-09-02"),
                    192,
                    "un_amor.webp"
            ),
            new LlibreRecord(
                    "9788433999542",
                    "La familia",
                    "La familia",
                    "«¡En esta familia no hay secretos!», proclama al inicio de este libro Damián, el padre, un hombre de ideas e ideales fijos obsesionado con la rectitud y la pedagogía. Pero esa casa sin secretos está en realidad llena de grietas, y la opresión que se respira entre sus paredes terminará creando vías de escape, códigos clandestinos, ocultaciones, fingimientos y mentiras. Formada por dos niñas, dos niños, una madre y un padre, esta familia en apariencia normal, de clase trabajadora y llena de buenas intenciones, es la protagonista de una novela coral que abarca varias décadas y en cuyas historias laten el deseo de libertad y la crítica a los pilares que tradicionalmente han sostenido, y todavía sostienen en gran medida, la institución familiar: autoritarismo y obediencia, vergüenza y silencio. Sara Mesa vuelve a demostrar que posee un ojo clínico para desnudar comportamientos humanos, detectar heridas latentes y retratar en toda su complejidad la fragilidad, las contradicciones y las flaquezas que nos conforman. Este libro es una nueva vuelta de tuerca a la construcción de uno de los universos literarios más potentes de las letras españolas actuales y la confirmación de un talento que no deja de crecer.",
                    "Summary in english",
                    LocalDate.parse("2022-09-14"),
                    232,
                    "la_familia.jpeg"
            ),
            new LlibreRecord(
                    "9788499309255",
                    "Jo, Robot",
                    "Me, Robot",
                    "La doctora Calvin és especialista en robopsicologia i una autoritat en la pràctica del cervell positrònic, que ha revolucionat el món dels robots. Tot i els continus progressos en aquest camp, els temors de la població humana no semblen justificats, ja que tots els robots duen impreses les Tres Lleis de la Robòtica, la primera de les quals és:\"Un robot no podrà causar mai dany a un ésser humà, o, per inacció seva, deixar que li passi res de dolent.\"Sembla descartada, per tant, l'existència de robots rebels...",
                    "Summary in english",
                    LocalDate.parse("1950-12-02"),
                    328,
                    "jo_robot.jpg"
            ),
            new LlibreRecord(
                    "9788497937306",
                    "Bóvedas de acero",
                    "Bóvedas de acero",
                    "Esta excelente novela de ciencia ficción de Isaac Asimov, maestro del género, es el segundo libro de la «Serie de los robots», primer bloque de su famosa «Saga de la Fundación». En el Enclave Espacial, a las afueras de la Ciudad de Nueva York, un científico de los Mundos Exteriores ha aparecido asesinado. El detective Elijah Baley tiene que ocuparse de este caso en la para él inquietante y odiosa compañía de un robot humanoide: R. Daneel Olivaw. La investigación es delicada ya que puede terminar con el equilibrio entre los descendientes de la colonización estelar, en perfecta comunión con sus robots, y los habitantes de la Tierra, que, refugiados en grandes metrópolis subterráneas a las que llaman Ciudades, sobreviven precariamente a la falta de recursos naturales y temen a los robots.",
                    "Summary in english",
                    LocalDate.parse("1954-06-01"),
                    272,
                    "bovedas.jpg"
            ),
            new LlibreRecord(
                    "9788497937856",
                    "El sol desnudo",
                    "El sol desnudo",
                    "Mientras la sociedad terrestre rechaza a los robots humanoides, los Mundos Exteriores, antiguas colonias de la Tierra, han basado su economía en el trabajo de los robots, desarrollando así una sociedad altamente tecnológica, mucho más que la terrestre, en la que los individuos no soportan la presencia de sus congeneres: todos los contactos sociales se producen por medio de proyecciones holográficas. Por eso, el detective Baley no sabe por dónde empezar cuando le envían a Solaria a resolver el primer asesinato que se produce en doscientos años, pues todo parece apuntar, paradójicamente, a que ha sido cometido por un robot.",
                    "Summary in english",
                    LocalDate.parse("1957-01-01"),
                    288,
                    "el_sol_desnudo.jpg"
            )
    );

    public List<LlibreRecord> selectAll(){
        return llibreRecordList;
    }

    public LlibreRecord select(String isbn) {
        for(LlibreRecord llibreRecord : llibreRecordList) {
            if(llibreRecord.getIsbn().equals(isbn)) {
                return llibreRecord;
            }
        }
        return null;
    }
}