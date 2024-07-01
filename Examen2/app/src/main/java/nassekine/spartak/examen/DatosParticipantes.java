package nassekine.spartak.examen;

import java.util.ArrayList;

public class DatosParticipantes {

    private static ArrayList<Participante> participant;


    public static ArrayList<Participante> getParticipantes() {

        if (participant == null) {
            participant = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                // se inicializan los participantes
                participant.add(new Participante("Participante " + (i + 1),  i));
            }

        }
        return participant;

    }

}
