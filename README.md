# DeathBanPlugin
A Paper plugin that automatically bans players when they die, with configurable duration, messages, and player-specific rules.

## Compatibility
| Minecraft Version | Server Software | Status |
|--------------------|-----------------|--------|
| 1.21.x             | Paper           | Works  |
| 26.x                | Paper           | Works  |

## Commands
| Command | Description | Usage | Permission |
|---|---|---|---|
| `/⁠autoban` | Toggle autoban on or off | `/⁠autoban` | `deathBanPlugin.admin` |
| `/⁠banmessage` | Set the ban message shown to players | `/⁠banmessage <message>` | `deathBanPlugin.admin` |
| `/⁠bantime` | Set ban duration in minutes (0 = permanent) | `/⁠bantime <minutes>` | `deathBanPlugin.admin` |
| `/⁠excludefromban` | Add a player to the exclude list | `/⁠excludefromban <ign>` | `deathBanPlugin.admin` |
| `/⁠removefromexclude` | Remove a player from the exclude list | `/⁠removefromexclude <ign>` | `deathBanPlugin.admin` |
| `/⁠onlyban` | Add a player to the only-ban list (restricts autoban to this list) | `/⁠onlyban <ign>` | `deathBanPlugin.admin` |
| `/⁠removefromonlyban` | Remove a player from the only-ban list | `/⁠removefromonlyban <ign>` | `deathBanPlugin.admin` |
| `/⁠reload` | Reload the config file | `/⁠reload` | `deathBanPlugin.admin` |
| `/⁠unban` | Unban a specific player or all banned players (leave it blank to unban all) | `/⁠unban <ign>` | `deathBanPlugin.admin` |

All settings (autoban toggle, ban message, ban time, exclude list, only-ban list) are fully configurable in `config.yml` and can be reloaded with `/⁠reload`.
