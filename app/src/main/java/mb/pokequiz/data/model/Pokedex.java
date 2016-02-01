package mb.pokequiz.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mbpeele on 2/1/16.
 */
public class Pokedex {

    private String created;
    private String modified;
    private String name;
    @SerializedName(SerializedNames.POKEDEX_ITEMS) private List<DexItem> dexItems;
    @SerializedName(SerializedNames.RESOURCE_URI) private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String resource_uri) {
        this.uri = resource_uri;
    }

    public List<DexItem> getDexItems() {
        return dexItems;
    }

    public void setPokemon(List<DexItem> pokemon) {
        this.dexItems = pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public static class DexItem {

        private String name;
        @SerializedName(SerializedNames.RESOURCE_URI) private String uri;

        public String getUri() {
            return uri;
        }

        public void setUri(String resource_uri) {
            this.uri = resource_uri;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
