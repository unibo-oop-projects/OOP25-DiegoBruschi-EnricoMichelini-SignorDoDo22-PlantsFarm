package it.unibo.Controller;

import it.unibo.Model.Encyclopedia;
import it.unibo.Model.Plant;
import it.unibo.Model.PlantType;
import it.unibo.Visual.EncyclopediaFrame;
import it.unibo.Visual.Texture;
import it.unibo.Visual.TextureUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EncyclopediaController {

    private final EncyclopediaFrame encyclopediaFrame;
    private final Encyclopedia encyclopedia;

    private int currentStage = 0;
    private Plant selectedPlant = null;

    public EncyclopediaController(EncyclopediaFrame view, Encyclopedia model) {
        this.encyclopediaFrame = view;
        this.encyclopedia = model;

        setupView();
        setupListeners();
    }

    // Instructions for putting buttons and their corresponding icons
    private void setupView() {
        for (Plant currentPlant : encyclopedia.getPlants()) {
            
            PlantType currentType = currentPlant.getType();

            ImageIcon coloredIcon = Texture.getPlantIcon(currentType.getIconFileName(), Texture.PLANT_LIST_DIMENSION);
            
            ImageIcon iconToShow = currentPlant.isDiscovered() ? coloredIcon : TextureUtils.setLockedIcon(coloredIcon);
            String buttonText = currentPlant.isDiscovered() ? currentType.getDisplayName() : "???";

            JButton currentButton = encyclopediaFrame.addPlantButton(buttonText, iconToShow);

            currentButton.addActionListener(e -> {
                selectPlant(currentPlant); //Show main information of the current plant

                if (currentPlant.isDiscovered()) {
                    currentButton.setIcon(coloredIcon); //Change button icon if plant is discovered
                }
            });
        }
    }

    //Instructions to show the next stage of the current plant
    private void setupListeners() {
        encyclopediaFrame.addStageButtonListener(e -> {
            if (selectedPlant == null) return; //TO DO ERROR

            int maxGrowthStage = selectedPlant.getMaxGrowthStage();
            currentStage = (currentStage + 1) % maxGrowthStage;

            encyclopediaFrame.setStageButtonText("Stage " + (currentStage + 1));
            
            updatePlantImage();
        });
    }

    //Instructions to show the main information of the current plant
    private void selectPlant(Plant selectedPlant) {
        if (!selectedPlant.isDiscovered()) return;

        this.selectedPlant = selectedPlant;
        this.currentStage = 0;

        encyclopediaFrame.showStageButton();
        encyclopediaFrame.setStageButtonText("Stage 1");

        PlantType plantType = selectedPlant.getType();
        
        updatePlantImage();
        
        encyclopediaFrame.setPlantDescription(
            "<html><b>" + plantType.getDisplayName() + "</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
            "<b>Rarity:</b>&nbsp;&nbsp;" + selectedPlant.getRarity() + "<br><br>" +
            "<b>Description:</b>&nbsp;&nbsp;" + plantType.getDescription() + "<br><br>" +
            "<b>Category:</b>&nbsp;&nbsp;" + plantType.getCategory() +
            "</html>"
        );

    }

    private void updatePlantImage() {
        if (selectedPlant == null) return;
        
        PlantType type = selectedPlant.getType();
        String plantName = type.getDisplayName();
        
        String folderPath = "plantsStage/" + plantName + "Stage/";
        String fileName = plantName + (currentStage + 1) + ".png";
        String fullPath = folderPath + fileName;

        ImageIcon stageIcon = Texture.createIconFromPath(fullPath, Texture.PLANT_DETAIL_DIMENSION, Texture.PLANT_DETAIL_DIMENSION);
        
        //FOR TEST, PUT ERROR
        if (stageIcon != null) {
            encyclopediaFrame.setPlantImage(stageIcon);
        } else {
            encyclopediaFrame.setPlantImage(Texture.getPlantIcon(type.getIconFileName(), Texture.PLANT_DETAIL_DIMENSION));
        }
    }

    // TO DO: getter per la pianta selezionata
    public Plant selected_plant() {
        return selectedPlant;
    }
}




