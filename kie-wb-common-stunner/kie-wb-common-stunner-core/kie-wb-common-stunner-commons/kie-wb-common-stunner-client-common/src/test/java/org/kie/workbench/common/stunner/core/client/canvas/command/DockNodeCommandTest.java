/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.stunner.core.client.canvas.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.stunner.core.graph.Node;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DockNodeCommandTest extends AbstractCanvasCommandTest {

    private static final String P_ID = "p1";
    private static final String ID = "e1";

    @Mock
    private Node parent;
    @Mock
    private Node candidate;

    private DockNodeCommand tested;

    @Before
    public void setup() throws Exception {
        super.setup();
        when(candidate.getUUID()).thenReturn(ID);
        when(parent.getUUID()).thenReturn(P_ID);
        this.tested = new DockNodeCommand(parent,
                                          candidate);
    }

    @Test
    public void testGetGraphCommand() {
        final org.kie.workbench.common.stunner.core.graph.command.impl.DockNodeCommand graphCommand =
                (org.kie.workbench.common.stunner.core.graph.command.impl.DockNodeCommand) tested.newGraphCommand(canvasHandler);
        assertNotNull(graphCommand);
        assertEquals(candidate,
                     graphCommand.getCandidate());
        assertEquals(parent,
                     graphCommand.getParent());
    }

    @Test
    public void testGetCanvasCommand() {
        final CanvasDockNodeCommand canvasCommand =
                (CanvasDockNodeCommand) tested.newCanvasCommand(canvasHandler);
        assertNotNull(canvasCommand);
        assertEquals(candidate,
                     canvasCommand.getCandidate());
        assertEquals(parent,
                     canvasCommand.getParent());
    }
}
