/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2015 Serge Rieder (serge@jkiss.org)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (version 2)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jkiss.dbeaver.ui.dnd;

import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.widgets.Display;
import org.jkiss.dbeaver.model.DBPNamedObject;

import java.util.Collection;

/**
 * Used to move DBSObject around in a database navigator.
 */
public final class DatabaseObjectTransfer extends LocalObjectTransfer<Collection<DBPNamedObject>> {

	private static final DatabaseObjectTransfer INSTANCE = new DatabaseObjectTransfer();
	private static final String TYPE_NAME = "DBSObject Transfer"//$NON-NLS-1$
			+ System.currentTimeMillis() + ":" + INSTANCE.hashCode();//$NON-NLS-1$
	private static final int TYPEID = registerType(TYPE_NAME);

	/**
	 * Returns the singleton instance.
	 *
	 * @return The singleton instance
	 */
	public static DatabaseObjectTransfer getInstance() {
		return INSTANCE;
	}

	private DatabaseObjectTransfer() {
	}

	/**
	 * @see org.eclipse.swt.dnd.Transfer#getTypeIds()
	 */
	@Override
    protected int[] getTypeIds() {
		return new int[] { TYPEID };
	}

	/**
	 * @see org.eclipse.swt.dnd.Transfer#getTypeNames()
	 */
	@Override
    protected String[] getTypeNames() {
		return new String[] { TYPE_NAME };
	}

    public static Collection<DBPNamedObject> getFromClipboard()
    {
        Clipboard clipboard = new Clipboard(Display.getDefault());
        try {
            return (Collection<DBPNamedObject>) clipboard.getContents(DatabaseObjectTransfer.getInstance());
        } finally {
            clipboard.dispose();
        }
    }

}
