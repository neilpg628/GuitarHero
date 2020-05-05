// This program constructs a Guitar object that it allows the user to play.

import java.util.*;
public class GuitarHero {
    public static void main(String[] args) {
        
        System.out.println("Enter 0 for two string, 1 for 37 string:");
        Scanner console = new Scanner(System.in);
        Guitar g = console.nextInt() == 0 ?  new GuitarLite() : new Guitar37();
        
        // this is an infinite loop--user must quit the application
        for (;;) {
            // check if the user has typed a key; if so, process it   
            if (StdDraw.hasNextKeyTyped()) {
                char key = Character.toLowerCase(StdDraw.nextKeyTyped());
                if (g.hasString(key)) {
                    g.pluck(key);
                } else {
                    System.out.println("bad key: " + key);
                }
            }
            // send the result to the sound card
            StdAudio.play(g.sample());
            g.tic();
        }
    }
}