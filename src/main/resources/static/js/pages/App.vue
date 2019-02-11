<template>
    <v-app>
        <v-toolbar app dark>
            <v-toolbar-title>Band Success</v-toolbar-title>
            <v-spacer></v-spacer>
            <span v-if="profile">{{profile.name}}</span>
            <v-btn v-if="profile" icon href="/logout">
                <v-icon>
                    exit_to_app
                </v-icon>
            </v-btn>
        </v-toolbar>
        <v-content>
            <v-container v-if="!profile">Needed authorize through the
                <a href="/login">Google</a>
            </v-container>
            <v-container v-if="profile">
                <messages-list :messages="messages"/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import MessagesList from "components/messages/MessagesList.vue"
    import {addHandler} from "../../util/ws";

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: frontData.messages,
                profile: frontData.profile
            }
        },
        created(){
            addHandler(data=> {
                if(data.objectType === 'MESSAGE') {
                    const index = this.messages.findIndex(item => item.id === data.body.id)
                    switch(data.eventType) {
                        case 'CREATE':
                        case 'UPDATE':
                            if(index > -1){
                                this.messages.splice(index, 1, data.body)
                            } else {
                                this.messages.push(data.body)
                            }
                            break
                        case 'REMOVE':
                            if( index > -1) {
                                this.messages.splice(index, 1)
                            }
                            break
                        default:
                            console.log(`Unknown event type : "${data.eventType}"`)
                    }
                    } else {
                        console.log(`Unknown object type : "${data.objectType}"`)
                    }
            })
        }
    }
</script>

<style>
</style>