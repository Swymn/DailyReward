package fr.swynn.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.swynn.model.Reward;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> {

    protected static final ObjectMapper MAPPER;

    protected final String fileName;
    protected final String filePath;
    protected final List<T> data;

    static {
        MAPPER = new ObjectMapper().findAndRegisterModules();
    }

    protected Repository(final String fileName, final Class<T> clazz) {
        this.fileName = "rewards.json";
        this.filePath = "plugins/DailyRewards/" + fileName;
        this.data = deserialize(clazz);
    }

    /**
     * Deserialize users from the file.
     *
     * @return The list of users
     * @throws RuntimeException If an error occurs
     */
    protected List<T> deserialize(final Class<T> clazz) {
        try {
            initializeDataStorage();
            var file = new File(filePath);
            if (file.length() == 0) {
                return new ArrayList<>();
            }

            var type = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            return MAPPER.readValue(file, type);
        } catch (final Exception e) {
            throw new RuntimeException("Unable to deserialize " + clazz.getSimpleName() + ": " + e.getMessage());
        }
    }

    /**
     * Serialize the users to the file.
     *
     * @throws RuntimeException If an error occurs
     */
    protected void serialize() throws RuntimeException {
        try {
            initializeDataStorage();
            var file = new File(filePath);

            MAPPER.writeValue(file, data);
        } catch (final Exception e) {
            throw new RuntimeException("Unable to serialize users");
        }
    }

    /**
     * Initialize the data storage.
     *
     * @throws RuntimeException If we are unable to create the file / folder.
     */
    protected void initializeDataStorage() throws RuntimeException {
        var file = new File(filePath);
        var dir = file.getParentFile();

        createFolderIfNotExist(dir);
        createFileIfNotExist(file);
    }

    /**
     * Create the folder if it does not exist.
     *
     * @param dir The folder
     * @throws RuntimeException If we are unable to create the folder.
     */
    private void createFolderIfNotExist(final File dir) throws RuntimeException {
        if (!dir.exists() && (!dir.mkdirs())) {
            throw new RuntimeException("Unable to create folder " + dir.getAbsolutePath());
        }
    }

    /**
     * Create the file if it does not exist.
     *
     * @param file The file
     * @throws RuntimeException If we are unable to create the file.
     */
    private void createFileIfNotExist(final File file) throws RuntimeException {
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    throw new RuntimeException("Unable to create file " + file.getAbsolutePath());
                }
            } catch (final Exception e) {
                throw new RuntimeException("Unable to create file " + file.getAbsolutePath());
            }
        }
    }
}
