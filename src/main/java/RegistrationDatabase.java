import java.util.*;
import java.util.stream.Collectors;

public class RegistrationDatabase {

    private Map<UUID, Registration> registrations = new HashMap<UUID, Registration>();

    public void addRegistration(Registration r) {
        registrations.put(r.getUUID(), r);
    }

    public Registration getRegistration(UUID uuid) {
        return registrations.get(uuid);
    }

    public void removeRegistration(UUID uuid) {
        registrations.remove(uuid);
    }

    public Collection<Registration> listRegistrations() {
        return this.registrations.values();
    }

}
