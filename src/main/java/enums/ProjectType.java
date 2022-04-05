package enums;

public enum ProjectType {
    SINGLE(1),
    SINGLE_WITH_BASELINE (2),
    MULTIPLE(3);

    int projectType;

    ProjectType(int projectType) {
        this.projectType=projectType;
    }

    public int getProjectType() {
        return projectType;
    }

    public String toString () {
        switch (this){
            case SINGLE:
                return "1";
            case SINGLE_WITH_BASELINE:
                return "2";
            case MULTIPLE:
                return "3";
            default:
                return "-1";
        }


    }
}
