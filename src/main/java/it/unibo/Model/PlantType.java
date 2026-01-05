package it.unibo.Model;

public enum PlantType {

    CARROT("Carrot", "Orange root that grows underground", "Carrots.png", "Vegetable"),
    TOMATO("Tomato", "Red fruit used in every dish", "Tomato.png", "Fruit"),
    CORN("Corn", "Yellow fruit made up of many kernels", "Corn.png", "Fruit"),
    ONION("Onion", "Whitish bulb that makes you cry when cut", "Onion.png", "Vegetable"),
    POTATO("Potato", "Tuber that grows underground, versatile in cooking", "Potato.png", "Vegetable"),
    PUMPKIN("Pumpkin", "Orange fruit used for Halloween decorations and soups", "Pumpkin.png", "Fruit"),
    PEPPER("Pepper", "Fruit of various colors used for salads or baked in the oven", "Pepper.png", "Fruit"),
    EGGPLANT("Eggplant", "Purple fruit used in many recipes", "Eggplant.png", "Fruit"),
    RADISH("Radish", "Small reddish root used in salads", "Raddish.png", "Vegetable"),
    ZUCCHINI("Zucchini", "Elongated green fruit often baked in the oven", "Zucchini.png", "Fruit"),

    MONSTERA("Monstera", "Tropical plant with large, characteristic split leaves", "Monstera.png", "Ornamental"),
    ORCHID("Orchid", "Flower of various colors that thrives in humid environments", "Orchid.png", "Ornamental"),
    BEGONIA("Begonia", "Decorative plant with characteristic spotted leaves", "Begonia.png", "Ornamental"),
    CACTUS("Cactus", "Spiny plant that thrives in arid environments", "Cactus.png", "Ornamental"),
    BONSAI("Bonsai", "Small tree constantly pruned to maintain its short stature", "Bonsai.png", "Ornamental"),
    HIBISCUS("Hibiscus", "Large colorful flower with a tall pistil", "Hibiscus.png", "Ornamental"),
    ANTHURIUM("Anthurium", "Heart-shaped flower that is slightly poisonous", "Antharium.png", "Ornamental"),
    ALOE("Aloe", "Medicinal plant and the base of many skincare creams", "Aloe.png", "Ornamental"),
    SNAKEPLANT("Snakeplant", "Striped plant characterized by its ease of propagation", "SnakePlant.png", "Ornamental"),
    NEPENTHES("Nepenthes", "Carnivorous plant, at the end of each leaf has pitchers capable of eating insects", "Nephentes.png", "Ornamental"),
    STRELITZIA("Strelitzia", "Tropical plant with large banana-like leaves and striking orange and blue flowers shaped like a bird", "Strelitzia.png", "Ornamental"),

    CHERRY("Cherry", "Small red fruit with a sweet or tart flavor, often eaten fresh or in desserts", "Cherry.png", "Fruit"),
    MANGO("Mango", "Tropical fruit with sweet, juicy flesh and a large pit, popular in smoothies and salads", "Mango.png", "Fruit"),
    AVOCADO("Avocado", "Creamy green fruit rich in healthy fats, commonly used in salads and spreads", "Avocado.png", "Fruit"),
    BUDDHAHAND("Buddha's Hand", "Unusual citron fruit with finger-like segments and fragrant zest, often used for decoration or flavoring", "BuddhaHand.png", "Fruit"),
    DRAGONFRUIT("Dragonfruit", "Exotic fruit with vibrant pink skin and speckled white or red flesh, mildly sweet", "DragonFruit.png", "Fruit"),
    APPLE("Apple", "Crunchy fruit that comes in many varieties, sweet or tart, eaten fresh or cooked", "Apple.png", "Fruit"),
    WATERMELON("Watermelon", "Large green fruit with juicy red flesh and black seeds, perfect for hot summer days", "Watermelon.png", "Fruit"),
    FIG("Fig", "Soft, sweet fruit with a unique texture and tiny seeds, eaten fresh or dried", "Fig.png", "Fruit");

    private final String displayName;
    private final String description;
    private final String iconFileName;
    private final String category;

    PlantType(String displayName, String description, String iconFileName, String category) {
        this.displayName = displayName;
        this.description = description;
        this.iconFileName = iconFileName;
        this.category = category;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public String getIconFileName() {
        return iconFileName;
    }

    public String getCategory() {
        return category;
    }
}



