public enum RoleType {
    DEVELOPER("Разработчик"),
    ANALYST("Аналитик"),
    TESTER("Тестировщик"),
    MANAGER("Менеджер"),
    DESIGNER("Дизайнер"),
    DEFAULT("По умолчанию");
    private final String roleType;

    RoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }

    @Override
    public String toString() {
        return getRoleType();
    }
}