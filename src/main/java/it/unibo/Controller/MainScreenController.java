//DONE
package it.unibo.Controller;

import it.unibo.Model.Encyclopedia;
import it.unibo.Model.Storage;
import it.unibo.Visual.MainScreen;
import it.unibo.Visual.ShopMenuFrame;
import it.unibo.Visual.EncyclopediaFrame;
import it.unibo.Visual.StorageFrame;
import it.unibo.Visual.ExitMenuFrame;

public class MainScreenController {

    private MainScreen mainScreen;
    private Encyclopedia encyclopedia;
    private Storage storage;

    public MainScreenController(MainScreen mainScreen, Encyclopedia encyclopedia, Storage storage) {
        this.mainScreen = mainScreen;
        this.encyclopedia = encyclopedia;
        this.storage=storage;
        setupListeners();
    }

    private void setupListeners() {
        mainScreen.addEncyclopediaListener(e -> openEncyclopedia());
        mainScreen.addSettingsListener(e -> openExitMenu());
        mainScreen.addShopListener(e -> openShopMenu());
         mainScreen.addInventoryListener(e -> openStorage());
    }

    private void openEncyclopedia() {
        EncyclopediaFrame encyclopediaFrame = new EncyclopediaFrame(mainScreen.getFrame());
        new EncyclopediaController(encyclopediaFrame, encyclopedia);
        encyclopediaFrame.showEncyclopediaScreen();
    }

    private void openExitMenu() {
        ExitMenuFrame exitFrame = new ExitMenuFrame(mainScreen.getFrame());

        exitFrame.addResumeListener(e -> exitFrame.close());
        exitFrame.addExitListener(e -> System.exit(0));

        exitFrame.show();
    }

    private void openShopMenu() {
        ShopMenuFrame shopMenuFrame = new ShopMenuFrame(mainScreen.getFrame());
        new ShopController(shopMenuFrame, encyclopedia);
        shopMenuFrame.show();
    }

    private void openStorage() {
        StorageFrame storageFrame = new StorageFrame(mainScreen.getFrame());
        new StorageController(storageFrame, storage);
        storageFrame.show();
    }

}




