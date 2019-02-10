<template>
    <div>
        <div v-if="!profile">Needed authorize through the
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>
                {{profile.name}}&nbsp;<a href="/logout">Log Out</a>
            </div>
            <messages-list :messages="messages"/>
        </div>
    </div>
</template>

<script>
    import MessagesList from "components/messages/MessagesList.vue"
    import {addHandler} from "../../util/ws";
    import { getIndex } from "../../util/collections"

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
                let index = getIndex(this.messages, data)
                if(index > -1){
                    this.messages.splice(index, 1, data)
                }else{
                    this.messages.push(data)
                }
            })
        }
    }
</script>

<style>

</style>