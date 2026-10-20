A Paper plugin that automatically bans players when they die, with configurable duration, messages, and player-specific rules.

## Building

Install JDK 21 and JDK 25 so Gradle can select the matching Java toolchain.

```bash
./gradlew build            # builds every version
./gradlew :1_21:build      # outputs 1_21/build/libs/deathBanPlugin-1.21.x.jar
./gradlew :26_1:build      # outputs 26_1/build/libs/deathBanPlugin-26.1.jar
./gradlew :26_2:build      # outputs 26_2/build/libs/deathBanPlugin-26.2.jar
./gradlew :26_3:build      # outputs 26_3/build/libs/deathBanPlugin-26.3.jar
```

On Windows, use `gradlew.bat` in place of `./gradlew`.

The build runs the shared death-ban tests against each module's Paper API. Run `./gradlew test` to run just the tests.

For a local development server, use the matching module's `runServer` task, for example `./gradlew :26_3:runServer`.

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
