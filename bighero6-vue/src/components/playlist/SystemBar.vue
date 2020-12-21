<template>
<v-system-bar window  dark>
    <v-menu offset-y transition="slide-y-transition">
      <v-btn  small right slot="activator">
        <v-icon dark>headset</v-icon>
      </v-btn>
      <v-list>
        <v-list-tile >
          <v-list-tile-title>About</v-list-tile-title>
        </v-list-tile>
        <v-dialog  max-width="300">
          <v-card>
            <v-card-title><h2>Music Player</h2></v-card-title>
            <v-card-text>Version 1.0.0</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn  >OK</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-list>
    </v-menu>
    <v-spacer></v-spacer>
 MUSIC PLAYER
    <v-spacer></v-spacer>
    <v-icon>remove</v-icon>
    <v-icon>check_box_outline_blank</v-icon>
    <v-icon>close</v-icon>
  </v-system-bar>
  </template>