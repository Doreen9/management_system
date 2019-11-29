package io.turntabl;

import io.turntabl.v1.Client;
import io.turntabl.v1.ClientController;
import io.turntabl.v2.Implementors.ClientDAOImpl;
import io.turntabl.v2.Transfers.ClientTO;
import io.turntabl.v3.Menus.Menus;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        Menus menus = new Menus();
        menus.main();

    }

}

