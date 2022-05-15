package timisimys.beacons.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import timisimys.beacons.Main;


public class settings implements CommandExecutor {

    public settings(Main settings) {
        settings.getCommand("settings").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            System.out.println("Данная команда может быть использована только в игре");
            return true;
        }
        Player player = (Player) sender;

        if(player.isOp()){

            if(args.length < 1) {
                player.sendMessage(ChatColor.RED + "Вы не указали тип настройки");
                return false;
            } else if(args[0].equals("chest_lapis")) {

                Location location = player.getLocation();
                Location location_block = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());

                String latis_first = Main.getInstence().getConfig().getString("location.casts-latis.first");
                String latis_second = Main.getInstence().getConfig().getString("location.casts-latis.second");

                if(args.length < 2) {
                    if (latis_first.equals("none")) {
                        Main.getInstence().getConfig().set("location.casts-latis.first",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна первого сундука с лазуритом");
                    } else if (latis_second.equals("none")) {
                        Main.getInstence().getConfig().set("location.casts-latis.second",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна второго сундука с лазуритом");
                    } else {
                        player.sendMessage(ChatColor.RED + "Укажите координаты какого сундука с лазуритом вы хотите обновить [first | second]");
                        return false;
                    }
                } else {
                    if (args[1].equals("first")) {

                        Main.getInstence().getConfig().set("location.casts-latis.first",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна первого сундука с лазуритом");

                    } else if (args[1].equals("second")) {

                        Main.getInstence().getConfig().set("location.casts-latis.second",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна второго сундука с лазуритом");

                    } else {

                        player.sendMessage(ChatColor.RED + "Вы неправильно указали аргумент для выбора сундука");
                        return false;
                    }
                }

            } else if(args[0].equals("chest_poison")) {

                Location location = player.getLocation();
                Location location_block = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());

                String poison_first = Main.getInstence().getConfig().getString("location.chest_poison.first");
                String poison_second = Main.getInstence().getConfig().getString("location.chest_poison.second");

                if(args.length < 2) {
                    if (poison_first.equals("none")) {
                        Main.getInstence().getConfig().set("location.chest_poison.first",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна первого сундука с зельями");
                    } else if (poison_second.equals("none")) {
                        Main.getInstence().getConfig().set("location.chest_poison.second",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна второго сундука с зельями");
                    } else {
                        player.sendMessage(ChatColor.RED + "Укажите координаты какого сундука с зельями вы хотите обновить [first | second]");
                        return false;
                    }
                } else {
                    if (args[1].equals("first")) {

                        Main.getInstence().getConfig().set("location.chest_poison.first",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна первого сундука с зельями");

                    } else if (args[1].equals("second")) {

                        Main.getInstence().getConfig().set("location.chest_poison.second",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна второго сундука с зельями");

                    } else {

                        player.sendMessage(ChatColor.RED + "Вы неправильно указали аргумент для выбора сундука");
                        return false;
                    }
                }
            } else if(args[0].equals("chest_exp")) {

                Location location = player.getLocation();
                Location location_block = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());

                String exp_first = Main.getInstence().getConfig().getString("location.chest_exp.first");
                String exp_second = Main.getInstence().getConfig().getString("location.chest_exp.second");

                if(args.length < 2) {
                    if (exp_first.equals("none")) {
                        Main.getInstence().getConfig().set("location.chest_exp.first",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна первого сундука с опытом");
                    } else if (exp_second.equals("none")) {
                        Main.getInstence().getConfig().set("location.chest_exp.second",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна второго сундука с опытом");
                    } else {
                        player.sendMessage(ChatColor.RED + "Укажите координаты какого сундука с опытом вы хотите обновить [first | second]");
                        return false;
                    }
                } else {
                    if (args[1].equals("first")) {

                        Main.getInstence().getConfig().set("location.chest_exp.first",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна первого сундука с опытом");

                    } else if (args[1].equals("second")) {

                        Main.getInstence().getConfig().set("location.chest_exp.second",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.GREEN + "Вы успешно обновили место спавна второго сундука с опытом");

                    } else {

                        player.sendMessage(ChatColor.RED + "Вы неправильно указали аргумент для выбора сундука");
                        return false;
                    }
                }

            } else if(args[0].equals("red_beacon")) {

                Location location = player.getLocation();
                Location location_block = new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ());

                String beacon_left = Main.getInstence().getConfig().getString("location.red_beacons.left");
                String beacon_right = Main.getInstence().getConfig().getString("location.red_beacons.right");

                if(args.length < 2) {
                    if (beacon_left.equals("none")) {
                        Main.getInstence().getConfig().set("location.red_beacons.left",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.RED + "[Красный]" + ChatColor.GREEN + " Вы успешно обновили место спавна левого маяка");
                    } else if (beacon_right.equals("none")) {
                        Main.getInstence().getConfig().set("location.red_beacons.right",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.RED + "[Красный]" + ChatColor.GREEN + " Вы успешно обновили место спавна правого маяка");
                    } else {
                        player.sendMessage(ChatColor.RED + "Укажите координаты какого сундука с опытом вы хотите обновить [left | right]");
                        return false;
                    }
                } else {
                    if (args[1].equals("left")) {

                        Main.getInstence().getConfig().set("location.red_beacons.left",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.RED + "[Красный]" + ChatColor.GREEN + " Вы успешно обновили место спавна левого маяка");

                    } else if (args[1].equals("right")) {

                        Main.getInstence().getConfig().set("location.red_beacons.right",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.RED + "[Красный]" + ChatColor.GREEN + " Вы успешно обновили место спавна правого маяка");

                    } else {

                        player.sendMessage(ChatColor.RED + "Вы неправильно указали аргумент для выбора маяка");
                        return false;
                    }
                }

            }else if(args[0].equals("blue_beacon")) {

                Location location = player.getLocation();
                Location location_block = new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ());

                String beacon_left = Main.getInstence().getConfig().getString("location.blue_beacons.left");
                String beacon_right = Main.getInstence().getConfig().getString("location.blue_beacons.right");

                if(args.length < 2) {
                    if (beacon_left.equals("none")) {
                        Main.getInstence().getConfig().set("location.blue_beacons.left",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.BLUE + "[Синий]" + ChatColor.GREEN + " Вы успешно обновили место спавна левого маяка");
                    } else if (beacon_right.equals("none")) {
                        Main.getInstence().getConfig().set("location.blue_beacons.right",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.BLUE + "[Синий]" + ChatColor.GREEN + " Вы успешно обновили место спавна правого маяка");
                    } else {
                        player.sendMessage(ChatColor.RED + "Укажите координаты какого сундука с опытом вы хотите обновить [left | right]");
                        return false;
                    }
                } else {
                    if (args[1].equals("left")) {

                        Main.getInstence().getConfig().set("location.blue_beacons.left",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.BLUE + "[Синий]" + ChatColor.GREEN + " Вы успешно обновили место спавна левого маяка");

                    } else if (args[1].equals("right")) {

                        Main.getInstence().getConfig().set("location.blue_beacons.right",location_block.getX() + "_" + location_block.getY() + "_" + location_block.getZ() + "_" + location_block.getBlock().getData());
                        Main.getInstence().saveConfig();
                        player.sendMessage(ChatColor.BLUE + "[Синий]" + ChatColor.GREEN + " Вы успешно обновили место спавна правого маяка");

                    } else {

                        player.sendMessage(ChatColor.RED + "Вы неправильно указали аргумент для выбора маяка");
                        return false;
                    }
                }

            }  else if(args[0].equals("spawn_red")) {

                Location location = player.getLocation();

                Main.getInstence().getConfig().set("location.spawn_red",location.getX() + "_" + location.getY() + "_" + location.getZ());
                Main.getInstence().saveConfig();
                player.sendMessage(ChatColor.RED + "[Спавн команды]" + ChatColor.GREEN + " Успешно установлен!");

            }  else if(args[0].equals("spawn_blue")) {

                Location location = player.getLocation();

                Main.getInstence().getConfig().set("location.spawn_blue",location.getX() + "_" + location.getY() + "_" + location.getZ());
                Main.getInstence().saveConfig();
                player.sendMessage(ChatColor.BLUE + "[Спавн команды]" + ChatColor.GREEN + " Успешно установлен!");

            }  else if(args[0].equals("lobby")) {

                Location location = player.getLocation();

                Main.getInstence().getConfig().set("location.lobby",location.getX() + "_" + location.getY() + "_" + location.getZ());
                Main.getInstence().saveConfig();
                player.sendMessage(ChatColor.RED + "[Лобби ожидания]" + ChatColor.GOLD + " Успешно установлено!");

            } else if(args[0].equals("world_game")) {

                if(args.length < 2) {
                    player.sendMessage(ChatColor.DARK_RED + "Вы не указали название мира");
                    return false;
                }

                Main.getInstence().getConfig().set("location.world_game", args[1]);
                Main.getInstence().saveConfig();
                player.sendMessage(ChatColor.GOLD + "[Игровой мир]" + ChatColor.RESET + " Успешно установлен!");

            } else if(args[0].equals("world_lobby")) {

                if(args.length < 2) {
                    player.sendMessage(ChatColor.DARK_RED + "Вы не указали название мира");
                    return false;
                }

                Main.getInstence().getConfig().set("location.world_lobby", args[1]);
                Main.getInstence().saveConfig();
                player.sendMessage(ChatColor.GOLD + "[Мир ожидания]" + ChatColor.RESET + " Успешно установлен!");

            }  else if(args[0].equals("game_center")) {

                Location loc = player.getLocation();

                Main.getInstence().getConfig().set("location.game_center", loc.getX() + "_" + loc.getY() + "_" + loc.getZ());
                Main.getInstence().saveConfig();
                player.sendMessage(ChatColor.GOLD + "[Центр игровой карты]" + ChatColor.RESET + " Успешно установлен!");

            }  else if(args[0].equals("dev")) {

                if(args.length < 2) {
                    player.sendMessage(ChatColor.DARK_RED + "Вы не указали состояние");
                    return false;
                }

                Main.getInstence().getConfig().set("game.dev", args[1]);
                Main.getInstence().saveConfig();
                player.sendMessage(ChatColor.GOLD + "[Режим разработки]" + ChatColor.RESET + " Успешно изменен!");

            } else if(args[0].equals("tp")) {

                if(args.length < 2) {
                    player.sendMessage(ChatColor.DARK_RED + "Вы не указали мир");
                    return false;
                }
                Bukkit.createWorld(new WorldCreator(args[1]));
                Location location = new Location(Bukkit.getWorld(args[1]), 0, 70 ,0);

                player.teleport(location);

                player.sendMessage(ChatColor.GOLD + "[Режим разработки]" + ChatColor.RESET + " Вы телепортировались в другой мир!!");

            } else {
                player.sendMessage(ChatColor.RED + "Вы указали некорректный аргемент");
            }

        } else {
            player.sendMessage(ChatColor.RED + "У вас нет прав!");
        }
        return false;
    }


}
