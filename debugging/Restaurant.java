package debugging;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * A class representing a restaurant with a limited capacity.
 */
public class Restaurant implements IRestaurant {

    private String name;
    private int numTables;

    private Collection<Party> upcomingReservations;
    private Collection<Party> seatedParties;
    private Queue<Party> waitingParties;

    private Random random;

    private Manager manager;

    /**
     * @param name         - the name of the restaurant
     * @param numTables    - the number of tables at the restaurant
     * @param reservations - a collection of parties who wish to dine at the
     *                     restaurant
     */
    public Restaurant(String name, int numTables, Collection<Party> reservations) {
        this.name = name;
        this.numTables = numTables;

        this.upcomingReservations = new LinkedList<Party>(reservations);
        this.seatedParties = new LinkedList<Party>();
        this.waitingParties = new LinkedList<Party>();

        this.manager = new Manager(this);
        this.random = new Random();

        this.openRestaurant();
    }

    /**
     * Open the restaurant for the night.
     */
    public void openRestaurant() {
        System.out.println(this + " is opening for the night.");
        this.manager.startManaging();
        System.out.println(this + " is closing down for the night.");
    }

    @Override
    public void arrive(Party party) {
        if (!this.upcomingReservations.contains(party)) {
            System.out.println(party + " does not have a reservation at " + this
                + "!");
            return;
        }

        System.out.print(party + " arrived at " + this + ". ");

        // Seat the arriving party if there is room; otherwise, make them wait
        if (this.seatedParties.size() > this.numTables) {
            this.wait(party);
        } else {
            this.seat(party);
        }
    }

    @Override
    public void depart(Party party) {
        if (!this.seatedParties.contains(party)) {
            System.out.println(party + " has not yet been seated at " + this + "!");
            return;
        }

        System.out.println(party + " departed from " + this + ".");
        this.seatedParties.remove(party);

        // Seat a waiting party if one exists
        if (!this.waitingParties.isEmpty()) {
            // the poll method returns and removes the first element in the queue
            this.seat(this.waitingParties.poll());
        }
    }

    /**
     * Adds party to the queue.
     *
     * @param - the party to add to the queue
     */
    private void wait(Party party) {
        System.out.println(party + " has to wait in line to be seated.");
        this.waitingParties.add(party);
    }

    /**
     * Seats the party.
     *
     * @param party - the party to seat
     */
    private void seat(Party party) {
        System.out.println(party + " was seated.");
        this.seatedParties.add(party);
    }

    /**
     * Select a random Party.
     *
     * @param parties - a collection of parties to choose from
     * @return a random Party in parties
     */
    private Party randomParty(Collection<Party> parties) {
        int partyIndex = this.random.nextInt(parties.size());

        // Create iterator and go to 0th element
        Iterator<Party> it = parties.iterator();
        Party current = it.next();

        // Find the element at partyIndex in the collection and return it
        for (int i = 0; i < partyIndex; i++) {
            current = it.next();
        }

        return current;
    }

    @Override
    public Party randomSeatedParty() {
        return this.randomParty(this.seatedParties);
    }

    @Override
    public Party randomReservationParty() {
        return this.randomParty(this.upcomingReservations);
    }

    /**
     * Check if a collection of parties is non-empty.
     *
     * @param parties - a collection of parties to check
     * @return true if the collection is non-empty
     */
    private boolean hasPartyMembers(Collection<Party> parties) {
        return !parties.isEmpty();
    }

    @Override
    public boolean hasUpcomingReservations() {
        return this.hasPartyMembers(this.upcomingReservations);
    }

    @Override
    public boolean hasSeatedParties() {
        return this.hasPartyMembers(this.seatedParties);
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static void main(String[] args) {
        String name = "Favorite Colors Restaurant";
        int numTables = 2;

        LinkedList<Party> reservations = new LinkedList<Party>();
        // The most bizarre interior paint shade names of all time
        // Source: buzzfeed
        reservations.add(new Party("hugs and kisses"));
        reservations.add(new Party("mayonnaise"));
        reservations.add(new Party("potentially purple"));
        reservations.add(new Party("dragons blood"));
        reservations.add(new Party("song of summer"));
        reservations.add(new Party("bath salts"));
        reservations.add(new Party("friendship"));
        reservations.add(new Party("anonymous"));
        reservations.add(new Party("nacho cheese"));
        reservations.add(new Party("grandma's sweater"));

        new Restaurant(name, numTables, reservations);
    }
}
