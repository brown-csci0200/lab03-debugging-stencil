package debugging;

/**
 * An interface for restaurants, where parties can arrive and depart.
 */
public interface IRestaurant {

    /**
     * A party arrives at the restaurant. They are seated if there is room,
     * otherwise they must wait in line. In either case, they should be removed
     * from the list of upcoming reservations.
     *
     * @param party - the arriving party
     */
    public void arrive(Party party);

    /**
     * A party departs from the restaurant. If there are parties waiting in line,
     * the first party in line should be seated. The party that departs from the
     * restaurant should be removed from the list of seated parties.
     *
     * @param party - the departing party
     */
    public void depart(Party party);

    /**
     * Gives back a random party from the seated parties.
     *
     * @return a random seated Party
     */
    public Party randomSeatedParty();

    /**
     * Gives back a random party from the list of reservations.
     *
     * @return a random Party that has a reservation
     */
    public Party randomReservationParty();

    /**
     * Checks to see if there are any outstanding reservations.
     *
     * @return true if there are outstanding reservations, false otherwise
     */
    public boolean hasUpcomingReservations();

    /**
     * Checks to see if there are any parties still seated.
     *
     * @return true if there are parties still seated, false otherwise
     */
    public boolean hasSeatedParties();
}
