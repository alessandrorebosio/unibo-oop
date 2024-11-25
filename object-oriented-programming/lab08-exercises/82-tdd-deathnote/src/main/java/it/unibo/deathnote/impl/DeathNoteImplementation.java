package it.unibo.deathnote.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {

    private final Map<String, Death> deaths = new LinkedHashMap<>();
    private String lastNameWritten;

    @Override
    public String getRule(int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size() - 1) {
            throw new IllegalArgumentException();
        }
        return RULES.get(ruleNumber);
    }

    @Override
    public void writeName(String name) {
        Objects.requireNonNull(name);
        this.lastNameWritten = name;
        this.deaths.put(name, new Death());
    }

    @Override
    public boolean writeDeathCause(String cause) {
        return updateDeath(cause, new DeathTransformer() {

            @Override
            public Death call(Death input) {
                return input.writeCause(cause);
            }

        });
    }

    @Override
    public boolean writeDetails(String details) {
        return updateDeath(details, new DeathTransformer() {

            @Override
            public Death call(Death input) {
                return input.writeDetails(details);
            }

        });
    }

    @Override
    public String getDeathCause(String name) {
        return getDeath(name).cause;
    }

    @Override
    public String getDeathDetails(String name) {
        return getDeath(name).details;
    }

    @Override
    public boolean isNameWritten(String name) {
        return deaths.containsKey(name);
    }

    private boolean updateDeath(final String update, final DeathTransformer operation) {
        if (lastNameWritten == null || update == null) {
            throw new IllegalStateException();
        }
        final var previous = deaths.get(lastNameWritten);
        final var updated = operation.call(previous);
        if (previous.equals(updated)) {
            return false;
        } else {
            deaths.put(lastNameWritten, updated);
            return true;
        }
    }

    private Death getDeath(final String name) {
        final var death = deaths.get(name);
        if (death == null) {
            throw new IllegalArgumentException(name + " has never been written in this notebook");
        }
        return death;
    }

    private interface DeathTransformer {
        Death call(final Death input);
    }

    private class Death {
        private static final long CAUSE_TIMEOUT = 40;
        private static final long DEATILS_TIMEOUT = 60000 + CAUSE_TIMEOUT;

        private final String cause;
        private final String details;
        private final long timeOfDeath;

        Death() {
            this("heart attack", "");
        }

        private Death(final String cause, final String details) {
            this.cause = cause;
            this.details = details;
            timeOfDeath = System.currentTimeMillis();
        }

        Death writeCause(final String cause) {
            return System.currentTimeMillis() < timeOfDeath + CAUSE_TIMEOUT ? new Death(cause, this.details) : this;
        }

        Death writeDetails(final String details) {
            return System.currentTimeMillis() < timeOfDeath + DEATILS_TIMEOUT ? new Death(this.cause, details) : this;
        }
    }

}
