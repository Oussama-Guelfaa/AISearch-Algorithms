package fr.emse.ai.search.bottle;

import java.util.Arrays;

// Represents the state of bottles with current volumes, capacities, and target volume
public class Bottles {

    private int[] volumes;
    private int[] capacities;
    private int targetVolume;
    public Bottles(int[] volumes, int[] capacities, int targetVolume) {
        this.volumes = Arrays.copyOf(volumes, volumes.length);
        this.capacities = Arrays.copyOf(capacities, capacities.length);
        this.targetVolume = targetVolume;
    }


    public Bottles(Bottles other) {
        this.volumes = Arrays.copyOf(other.volumes, other.volumes.length);
        this.capacities = Arrays.copyOf(other.capacities, other.capacities.length);
        this.targetVolume = other.targetVolume;
    }


    public int getHValue() {
        int minDifference = Integer.MAX_VALUE;

        for (int volume : volumes) {
            int difference = Math.abs(volume - targetVolume);
            if (difference < minDifference) {
                minDifference = difference;
            }

            // If any bottle has the target volume, the heuristic is 0
            if (volume == targetVolume) {
                return 0;
            }
        }

        return minDifference;
    }


    public int[] getVolumes() {
        return Arrays.copyOf(volumes, volumes.length);
    }


    public int[] getCapacities() {
        return Arrays.copyOf(capacities, capacities.length);
    }


    public int getTargetVolume() {
        return targetVolume;
    }


    public boolean hasTargetVolume() {
        for (int volume : volumes) {
            if (volume == targetVolume) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Bottles other = (Bottles) obj;
        return Arrays.equals(volumes, other.volumes) &&
               Arrays.equals(capacities, other.capacities) &&
               targetVolume == other.targetVolume;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(volumes);
        result = 31 * result + Arrays.hashCode(capacities);
        result = 31 * result + targetVolume;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bottles: [");
        for (int i = 0; i < volumes.length; i++) {
            sb.append(volumes[i]).append("/").append(capacities[i]);
            if (i < volumes.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("], Target: ").append(targetVolume);
        return sb.toString();
    }
}
