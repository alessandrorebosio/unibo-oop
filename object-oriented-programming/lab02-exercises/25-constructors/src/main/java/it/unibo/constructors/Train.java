package it.unibo.constructors;

class Train {

    static final int DEFAULT_N_FC_SEATS = 50;
    static final int DEFAULT_N_SC_SEATS = 100;
    static final int DEFAULT_TOT_SEATS = 150;

    final int nTotSeats;
    final int nFirstClassSeats;
    final int nSecondClassSeats;
    int nFirstClassReservedSeats;
    int nSecondClassReservedSeats;

    Train() {
        this(DEFAULT_TOT_SEATS, DEFAULT_N_FC_SEATS, DEFAULT_N_SC_SEATS);
    }

    Train(final int nFCSeats, final int nSCSeats) {
        this(nFCSeats + nSCSeats, nFCSeats, nSCSeats);
    }

    Train(final int nTotSeats, final int nFCSeats, final int nSCSeats) {
        this.nTotSeats = nTotSeats;
        this.nFirstClassSeats = nFCSeats;
        this.nSecondClassSeats = nSCSeats;
        this.nFirstClassReservedSeats = 0;
        this.nFirstClassReservedSeats = 0;
    }

    void deleteAllReservations() {
        this.nFirstClassReservedSeats = 0;
        this.nSecondClassReservedSeats = 0;
    }

    double getFirstClassOccupancyRatio() {
        return this.nFirstClassReservedSeats * 100d / this.nFirstClassSeats;
    }

    int getnFirstClassSeats() {
        return this.nFirstClassSeats;
    }

    int getnSecondClassSeats() {
        return this.nSecondClassSeats;
    }

    double getOccupancyRatio() {
        return (this.nFirstClassReservedSeats + this.nSecondClassReservedSeats) * 100d / this.nTotSeats;
    }

    double getSecondClassOccupancyRatio() {
        return this.nSecondClassReservedSeats * 100 / this.nSecondClassSeats;
    }

    int getTotalSeats() {
        return this.nTotSeats;
    }

    void printTrainInfo() {
        System.out.println("Train info:");
        System.out.println("-nTotSeats: " + this.nTotSeats);
        System.out.println("-nFCSeats: " + this.nFirstClassSeats);
        System.out.println("-nSCSeats: " + this.nSecondClassSeats);
        System.out.println("-nFCReservedSeats: " + this.nFirstClassReservedSeats);
        System.out.println("-nSCReservedSeats: " + this.nSecondClassReservedSeats + "\n");
    }

    public void reserveFirstClassSeats(final int nSeats) {
        if (this.nFirstClassReservedSeats + nSeats <= this.nFirstClassSeats) {
            this.nFirstClassReservedSeats += nSeats;
        }
    }

    public void reserveSecondClassSeats(final int nSeats) {
        if (this.nSecondClassReservedSeats + nSeats <= this.nSecondClassSeats) {
            this.nSecondClassReservedSeats += nSeats;
        }
    }
}
