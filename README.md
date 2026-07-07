A Paper plugin that automatically bans players when they die, with configurable duration, messages, and player-specific rules.

## Compatibility

This project is split into separate builds targeting different Minecraft/Java versions, all sharing the same source code:

| Module   | Minecraft Version | Server Software | Java | Status |
|----------|--------------------|------------------|------|--------|
| legacy   | 1.21.x             | Paper            | 21   | Works  |
| 26_1     | 26.1.x             | Paper            | 25   | Works  |
| modern   | 26.2+              | Paper            | 25   | Works  |

## Building

```bash
./gradlew :legacy:build   # outputs build/libs/deathBanPlugin-1.21.x.jar
./gradlew :26_1:build     # outputs build/libs/deathBanPlugin-26.1.jar
./gradlew :modern:build   # outputs build/libs/deathBanPlugin-26.x.jar
```

## Commands

| Command | Description | Usage | Permission |
|---|---|---|---|
| autoban | Toggle autoban on or off | autoban | deathBanPlugin.admin |
| banmessage | Set the ban message shown to players | banmessage message | deathBanPlugin.admin |
| bantime | Set ban duration in minutes (0 = permanent) | bantime minutes | deathBanPlugin.admin |
| excludefromban | Add a player to the exclude list | excludefromban <ign> | deathBanPlugin.admin |
| removefromexclude | Remove a player from the exclude list | removefromexclude <ign> | deathBanPlugin.admin |
| onlyban | Add a player to the only-ban list (restricts autoban to this list) | onlyban <ign> | deathBanPlugin.admin |
| removefromonlyban | Remove a player from the only-ban list | removefromonlyban <ign> | deathBanPlugin.admin |
| reload | Reload the config file | reload | deathBanPlugin.admin |
| unban | Unban a specific player or all banned players (leave blank to unban all) | unban <ign> | deathBanPlugin.admin |

All settings (autoban toggle, ban message, ban time, exclude list, only-ban list) are fully configurable in `config.yml` and can be reloaded with `/reload`.

## License
This project is licensed under the [MIT License](LICENSE).

## Author
Made by [micahcode](https://micahcode.com)
