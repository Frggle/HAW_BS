package ShopVL;
/*
 * Shop.java
 * Version 1.0
 Autor: M. Hübner
 Zweck: Stellt einen generischen Shop mit Zugriffsmethoden für Customer zur Verfügung
 */

import java.util.concurrent.*;

public class Shop {
   private static final int ANZAHL_KOERBE = 3;

   private Semaphore korbstapel;

   public Shop() {
      korbstapel = new Semaphore(ANZAHL_KOERBE);
   }

   // Customer ruft die Methode ENTER auf
   public void enter() throws InterruptedException {
      // Versuche, einen Korb zu bekommen. Falls Stapel auf Null ==> Warten!
      korbstapel.acquire();

      // Einkaufen
      System.err.println("                                             "
            + Thread.currentThread().getName() + " buys goods!");
      try {
         buyGoods();
         // Laden verlassen
         System.err
               .println("                                                                                   "
               + Thread.currentThread().getName() + " leaves shop!");
      } finally {
         korbstapel.release();
      }
   }

   // Customer benutzen diese Methode, um einzukaufen
   public void buyGoods() throws InterruptedException {
      int sleepTime = (int) (1000 * Math.random());

      // Ausführenden Thread blockieren
      Thread.sleep(sleepTime);
   }

}
