package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import jdk.jfr.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest{

    @Test
    void isPasswordValidTest(){
            assertFalse(Client.isPasswordValid("Clt1!"));
            assertTrue(Client.isPasswordValid("Client1!"));
            assertTrue(Client.isPasswordValid("Cl1234!"));
            assertFalse(Client.isPasswordValid("ClientC"));
            assertFalse(Client.isPasswordValid("client1!"));
            assertFalse(Client.isPasswordValid("Client!"));
            assertTrue(Client.isPasswordValid("cLient1!"));
            assertFalse(Client.isPasswordValid("Client1"));
            assertFalse(Client.isPasswordValid("CLIENT1!"));
            assertFalse(Client.isPasswordValid("ClientClientClientClientClientClientClientClientClientClient12345!!"));
    }

    @Test
    void confirmUserTest(){
        Client c = new Client("happy", "Gilmore12!", "charles@ithaca.edu");
        assertFalse(c.confirmUser("happy", "Partyguy123!"));
        assertTrue(c.confirmUser("happy", "Gilmore12!"));
    }

}