package com.future.scientists.swpeople;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonGenerator {

    private static final String[] names = new String[]{"Luke Skywalker", "C-3PO", "R2-D2", "Darth Vader", "Leia Organa", "Obi-Wan Kenobi"};
    private static final String[] avatars = new String[]{"https://www.prohandmade.ru/wp-content/uploads/2015/09/starwarsiconsawakens-16-900x900.png",
            "http://img.scoop.it/GroGTPKlZ4COVytp24pJdoXXXL4j3HpexhjNOf_P3YmryPKwJ94QGRtDb3Sbc6KY",
            "https://storage.googleapis.com/replit/images/1509526663063_78cee07358a3fd43480ae7fa5b4d76e3.png",
            "https://yt3.ggpht.com/a-/AJLlDp13bM8yFuEE7HVlYHFt7zvZPSMWbGEz4OrxOw=s900-mo-c-c0xffffffff-rj-k-no",
            "http://comic-cons.xyz/wp-content/uploads/Star-Wars-avatar-icon-Ewok.png",
            "https://cdn.dribbble.com/users/588874/screenshots/2249423/dribbble_1x.png",
            "https://avatarko.ru/img/kartinka/32/Star_Wars_pistolet_31680.jpg"};
    private static final String[] planets = new String[]{"Alderaan", "Yavin IV", "Stewjon", "Alderaan", "Endor", "Naboo", "Kamino", "Geonosis", "Tatooine"};
    private static final int minMass = 55;
    private static final int maxMass = 110;
    private static final int[] sounds = new int[]{R.raw.sound_14749, R.raw.sound_14769, R.raw.sound_15260, R.raw.sound_15261};

    private Random random = new Random();

    public Person getPerson() {
        final String name = names[random.nextInt(names.length)];
        final String avatar = avatars[random.nextInt(avatars.length)];
        final String planet = planets[random.nextInt(planets.length)];
        final int mass = minMass + random.nextInt(maxMass - minMass + 1);
        return new Person(name, avatar, planet, mass);
    }

    public List<Person> getPeople(final int count){
        List<Person> people = new ArrayList<>(count);
        for(int i=0; i < count; i++) {
            people.add(getPerson());
        }
        return people;
    }

    public String getAvatarLink(){
        return avatars[random.nextInt(avatars.length)];
    }

    public int getSound(){
        return sounds[random.nextInt(sounds.length)];
    }
}
