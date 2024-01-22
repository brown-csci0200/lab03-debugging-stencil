package debugging;

import java.util.Random;

/**
 * A Manager for a restaurant.
 * <p>
 * The restaurant manager observes the status of the restaurant. As long as
 * there are parties with reservations still waiting to be served, the manager
 * decides when they should arrive and when they should depart.
 */
public class Manager implements IManager {

    private static final int PARTY_ARRIVES = 1;
    private static final int PARTY_DEPARTS = 0;

    private Restaurant restaurant;
    private Random random;

    /**
     * @param restaurant - the restaurant to manage
     */
    public Manager(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.random = new Random();
    }

    @Override
    public void startManaging() {
        // Choose for a party to arrive or depart
        // Stop when both upcomingReservations and seatedParties are empty
        while (this.restaurant.hasUpcomingReservations() || this.restaurant.hasSeatedParties()) {
            int nextEvent = this.makeChoice();

            if (nextEvent == PARTY_ARRIVES) {
                this.partyArrives();
            } else if (nextEvent == PARTY_DEPARTS) {
                this.partyDeparts();
            }
        }
    }

    /**
     * Choose whether a party should arrive or a party should depart.
     *
     * @return either PARTY_ARRIVES or PARTY_DEPARTS
     */
    private int makeChoice() {
        // If everyone has arrived, only choose for parties to depart
        if (!this.restaurant.hasUpcomingReservations()
            && this.restaurant.hasSeatedParties()) {
            return PARTY_DEPARTS;
        }

        // If there are no parties seated, only choose for parties to arrive
        if (this.restaurant.hasUpcomingReservations() &&
            !this.restaurant.hasSeatedParties()) {
            return PARTY_ARRIVES;
        }

        // If not everyone has arrived, and there are still parties seated,
        // choose for parties to arrive twice as often as they depart
        return Math.min(this.random.nextInt(3), 2);
    }

    /**
     * Choose a party to arrive at the restaurant.
     */
    private void partyArrives() {
        Party arrivingParty = this.restaurant.randomReservationParty();
        this.restaurant.arrive(arrivingParty);
    }

    /**
     * Choose a party to depart the restaurant.
     */
    private void partyDeparts() {
        Party departingParty = this.restaurant.randomSeatedParty();
        this.restaurant.depart(departingParty);
    }
}
