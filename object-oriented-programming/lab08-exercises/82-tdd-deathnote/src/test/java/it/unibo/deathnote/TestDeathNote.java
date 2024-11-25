package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.lang.Thread.sleep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImplementation;

class TestDeathNote {

    private static final String ME = "Alessandro Rebosio";
    private static final String YOU = "You";

    private DeathNoteImplementation deathNote;

    @BeforeEach
    public void setUp() {
        this.deathNote = new DeathNoteImplementation();
    }

    @Test
    void testsRule() {
        int[] index = { 0, -1, DeathNote.RULES.size() + 1 };

        for (final int elem : index) {
            assertThrows(IllegalArgumentException.class, new Executable() {

                @Override
                public void execute() throws Throwable {
                    deathNote.getRule(elem);
                }

            });
        }
        for (final String rule : DeathNote.RULES) {
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test
    void testActualDeath() {
        assertFalse(deathNote.isNameWritten(ME));
        deathNote.writeName(ME);
        assertTrue(deathNote.isNameWritten(ME));
        assertFalse(deathNote.isNameWritten(YOU));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test
    void testDeathCause() throws InterruptedException {
        assertThrows(IllegalStateException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                deathNote.writeDeathCause("spontaneous combustion");
            }

        });
        deathNote.writeName(YOU);
        assertEquals("heart attack", deathNote.getDeathCause(YOU));
        deathNote.writeName(ME);
        assertTrue(deathNote.writeDeathCause("karting accident"));
        assertEquals("karting accident", deathNote.getDeathCause(ME));
        sleep(100);
        assertFalse(deathNote.writeDeathCause("Spontaneous human combustion"));
        assertEquals("karting accident", deathNote.getDeathCause(ME));
    }

    @Test
    void testDeathDetails() throws InterruptedException {
        assertThrows(IllegalStateException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                deathNote.writeDetails("spontaneous combustion");
            }

        });
        deathNote.writeName(YOU);
        assertEquals("", deathNote.getDeathDetails(YOU));
        assertTrue(deathNote.writeDetails("ran for too long"));
        assertEquals("ran for too long", deathNote.getDeathDetails(YOU));
        deathNote.writeName(ME);
        sleep(60100);
        assertFalse(deathNote.writeDetails("changed"));
        assertEquals("", deathNote.getDeathDetails(ME));
    }
}