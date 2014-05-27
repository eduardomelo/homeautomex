//using System;
//using System.Collections.Generic;
//using System.ComponentModel.DataAnnotations.Schema;
//using System.Data.Entity.ModelConfiguration;
//using System.Linq;
//using System.Text;
//using System.Threading.Tasks;
//using HomeAutomexLibrary.Entidade;

//namespace HomeAutomexLibrary.Repositorio.Map
//{
//    public class UTCenarioMap : EntityTypeConfiguration<UTCenario>
//    {
//        public UTCenarioMap()
//        {

//            ToTable("UT_CENARIO");

//            HasKey(e => e.Chave)
//                .Property(e => e.Chave)
//                .HasColumnName("CD_UTCENARIO")
//                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

//            Property(e => e.CD_Dispositivo).HasColumnName("CD_DISPOSITIVO").IsRequired();
//            Property(e => e.CD_Cenario).HasColumnName("CD_CENARIO");
//            Property(e => e.StatusDispositivo).HasColumnName("STATUS");


//            Ignore(e => e.DataCadastro);
//            Ignore(e => e.DataAlteracao);
//            Ignore(e => e.DataExclusao);
//        }

//    }
//}
