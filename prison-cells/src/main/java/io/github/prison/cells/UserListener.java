/*
 *  Prison is a Minecraft plugin for the prison game mode.
 *  Copyright (C) 2016 The Prison Team
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.prison.cells;

import com.google.common.eventbus.Subscribe;
import io.github.prison.Prison;
import io.github.prison.internal.events.PlayerJoinEvent;

/**
 * @author SirFaizdat
 */
public class UserListener {

    private CellsModule cellsModule;

    public UserListener(CellsModule cellsModule) {
        this.cellsModule = cellsModule;
    }

    public void init() {
        Prison.getInstance().getEventBus().register(this);
    }

    @Subscribe
    public void onPlayerJoin(PlayerJoinEvent e) {
        try {
            if (cellsModule.getUser(e.getPlayer().getUUID()) == null)
                cellsModule.saveCellUser(new CellUser(e.getPlayer().getUUID()));

            CellUser user = cellsModule.getUser(e.getPlayer().getUUID());
            cellsModule.saveCellUser(user);
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }

}