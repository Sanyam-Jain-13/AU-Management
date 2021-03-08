export class Opportunity {

    public id: number;
    public userId:number;
    public description: String;
    public date: String;
    public skills: any;
    public location: String;
    public minExperience: number;
    public demand: number;

    public constructor() {}

    public default () {
        this.id = 0;
        this.userId = 0;
        this.description = "";
        this.date = "";
        this.skills = "";
        this.location = "";
        this.minExperience = 0;
        this.demand = 0;
    }
}
